package Utilities;


import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;

import Factories.*;
import Grid.Position;
import Grid.Road;
import Components.Speed;
import ObserverPattern.Observer;
import StrategyPattern.FixedSellingStrategy;
import StrategyPattern.RandomSellingStrategy;

/**
 * This class is the creator of all objects in this project
 */
public class ControlSystem {

    private XMLReader xmlReader = new XMLReader();
    private ArrayList<Position> positionList = new ArrayList<>();
    private ArrayList<Speed> speedList = new ArrayList<>();
    private ArrayList<Road> roadList = new ArrayList<>();
    private ArrayList<Factory> factoryList = new ArrayList<>();
    private static ControlSystem instance;
    private FixedSellingStrategy fixedSellingStrategy;
    private RandomSellingStrategy randomSellingStrategy;
    private boolean readyToSell;

    /**
     * Instantiate a new Central System, parse the XML File and calls the method prepare()
     */
    public ControlSystem() {
        //Parse XML File
        try {
            xmlReader.parseXML("./src/ressources/configuration.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        prepare();
    }


    //Create factories, roads, observers, strategies

    /**
     * Create factories, roads, selling strategies and attach observers to Warehouse's list of observers
     */
    private void prepare() {
        try {
            this.positionList=createPositions();
            this.roadList=createRoads();
            this.speedList=createSpeeds();
            this.factoryList=createFactories();
            randomSellingStrategy=new RandomSellingStrategy();
            fixedSellingStrategy=new FixedSellingStrategy();

            Warehouse warehouse = (Warehouse) factoryList.get(0);
            for(int i=1; i<factoryList.size();i++){
                warehouse.attach((Observer) factoryList.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a single instance of Central System - Singleton Design Pattern
     * @return instance of Central System
     */
    public static ControlSystem getInstance() {
        if (instance == null) instance = new ControlSystem();
        return instance;
    }

    /**
     * Set Warehouse's selling strategy (Strategy) based on the strategy (String) given in parameter
     * The String strategy is the label of the button selected by the client
     * @param strategy strategy to be set
     */
    public void setStrategy(String strategy) {
        Warehouse warehouse= (Warehouse) this.factoryList.get(0);
        if (strategy.equals("Strategy 1")) warehouse.setSellingStrategy(fixedSellingStrategy);
        else if (strategy.equals("Strategy 2")) warehouse.setSellingStrategy(randomSellingStrategy);
        setReadyToSell(true);
    }

    /**
     * Set the field readyToSell to the given parameter
     * @param readyToSell boolean to be set
     */
    public void setReadyToSell(boolean readyToSell) {this.readyToSell = readyToSell;}

    /**
     * Get the boolean field readyToSell
     * @return this readyToSell value
     */
    public boolean isReadyToSell() {return readyToSell;}

    //Méthode appelée à chaque "tour"
    /**
     * Draw roads and give ability to factories to draw
     * Also sell airplanes when warehouse is ready to sell i.e. when a strategy is chosen
     * @param g Graphics
     */
    public void draw(Graphics g) {

        //Paint roads
        for(int i=0; i<factoryList.size();i++){
            if(factoryList.get(i).getId()!=51){
                ManufacturingFactory manufacturingFactory = (ManufacturingFactory) factoryList.get(i);
                g.drawLine(manufacturingFactory.getRoad().getPosStart().getPositionX(),manufacturingFactory.getRoad().getPosStart().getPositionY(),manufacturingFactory.getRoad().getPosFinish().getPositionX(),manufacturingFactory.getRoad().getPosFinish().getPositionY());
            }
        }

        //Paint factories
        for(int i=0; i<factoryList.size();i++){
            Factory factory= factoryList.get(i);
            factory.clock();
            factory.drawMaterials(g);
            factory.draw(g);

            // Client-Supplier inventory management
            if(factory.getId()!=51){
                //Special case Wing-Assembly inventory
                if(factory.getId()==21){
                    ManufacturingFactory manufacturingFactory = (ManufacturingFactory) factory;
                    AssemblyFactory assemblyFactory = (AssemblyFactory) manufacturingFactory.getClient();
                    assemblyFactory.addInputWing(manufacturingFactory.getDelivery());
                    manufacturingFactory.clearDelivery();
                }
                //Special case Engine-Assembly inventory
                if(factory.getId()==31){
                    ManufacturingFactory manufacturingFactory = (ManufacturingFactory) factory;
                    AssemblyFactory assemblyFactory = (AssemblyFactory) manufacturingFactory.getClient();
                    assemblyFactory.addInputEngine(manufacturingFactory.getDelivery());
                    manufacturingFactory.clearDelivery();
                } else {
                    ManufacturingFactory manufacturingFactory = (ManufacturingFactory) factory;
                    manufacturingFactory.getClient().addInput(manufacturingFactory.getDelivery());
                    manufacturingFactory.clearDelivery();
                }
            }
        }

        //Sell airplanes when warehouse is ready to sell
        // i.e. when a selling strategy is chosen
        Warehouse warehouse= (Warehouse) this.factoryList.get(0);
        if(this.isReadyToSell()) warehouse.sell();

    }

    /**
     * Create factories positions
     * @return list of positions
     */
    public ArrayList<Position> createPositions(){
        for (int i=0;i<XMLReader.factoryPositionList.size();i++){
            this.positionList.add(new Position(XMLReader.factoryPositionList.get(i).get(0), XMLReader.factoryPositionList.get(i).get(1)));
        }
        return this.positionList;
    }

    /**
     * Create factories roads
     * @return list of roads
     */
    public ArrayList<Road> createRoads(){
        //Hashmap to get a Position from an ID, useful for creating roads
        HashMap<Integer, Position> idToPosition = new HashMap<>();
        for (int i=0;i<XMLReader.factoryIdList.size();i++){
            idToPosition.put(XMLReader.factoryIdList.get(i),positionList.get(i));
        }
        //Create the roads
        for (int i=0;i<XMLReader.factoryRoadList.size();i++){
            this.roadList.add(new Road(idToPosition.get(XMLReader.factoryRoadList.get(i).get(0)), idToPosition.get(XMLReader.factoryRoadList.get(i).get(1))));
        }
        return this.roadList;
    }

    /**
     * Create speeds of products of each factory
     * @return list of speeds
     */
    public ArrayList<Speed> createSpeeds(){
        for (int i=0;i<XMLReader.factoryRoadList.size();i++){
            int speedX =0, speedY=0;
            Position posFinish= roadList.get(i).getPosFinish();
            Position posStart = roadList.get(i).getPosStart();
            if(posFinish.getPositionX()>posStart.getPositionX()){
                speedX=1;
            } else if(posFinish.getPositionX()<posStart.getPositionX()){
                speedX=-1;
            } else {
                speedX=0;
            }

            if(posFinish.getPositionY()>posStart.getPositionY()){
                speedY=1;
            } else if(posFinish.getPositionY()<posStart.getPositionY()){
                speedY=-1;
            } else {
                speedY=0;
            }
            this.speedList.add(new Speed(speedX,speedY));
        }
        return this.speedList;
    }

    /**
     * Create factories
     * @return list of factories
     */
    public ArrayList<Factory> createFactories(){

        factoryList.add(new Warehouse(
                XMLReader.factoryIconList.get(4).get(0),
                XMLReader.factoryIconList.get(4).get(1),
                XMLReader.factoryIconList.get(4).get(2),
                XMLReader.factoryIconList.get(4).get(3),
                positionList.get(3),
                XMLReader.factoryIdList.get(3),
                XMLReader.factoryInputQuantityList.get(4).get(0)));

        factoryList.add(new AssemblyFactory(
                XMLReader.factoryIconList.get(3).get(0),
                XMLReader.factoryIconList.get(3).get(1),
                XMLReader.factoryIconList.get(3).get(2),
                XMLReader.factoryIconList.get(3).get(3),
                positionList.get(2),
                XMLReader.factoryIdList.get(2),
                XMLReader.factoryInputQuantityList.get(3),
                XMLReader.factoryProductionTimeList.get(3),
                speedList.get(2),
                roadList.get(2),
                factoryList.get(0)));

        factoryList.add(new WingFactory(
                XMLReader.factoryIconList.get(1).get(0),
                XMLReader.factoryIconList.get(1).get(1),
                XMLReader.factoryIconList.get(1).get(2),
                XMLReader.factoryIconList.get(1).get(3),
                positionList.get(1),
                XMLReader.factoryIdList.get(1),
                XMLReader.factoryInputQuantityList.get(1),
                XMLReader.factoryProductionTimeList.get(1),
                speedList.get(1),
                roadList.get(1),
                factoryList.get(1)));

        factoryList.add(createMetalFactory(
                positionList.get(0),
                XMLReader.factoryIdList.get(0),
                speedList.get(0),
                roadList.get(0),
                factoryList.get(2)));

        factoryList.add(new EngineFactory(
                XMLReader.factoryIconList.get(2).get(0),
                XMLReader.factoryIconList.get(2).get(1),
                XMLReader.factoryIconList.get(2).get(2),
                XMLReader.factoryIconList.get(2).get(3),
                positionList.get(6),
                XMLReader.factoryIdList.get(6),
                XMLReader.factoryInputQuantityList.get(2),
                XMLReader.factoryProductionTimeList.get(2),
                speedList.get(5),
                roadList.get(5),
                factoryList.get(1)));

        factoryList.add(createMetalFactory(
                positionList.get(4),
                XMLReader.factoryIdList.get(0),
                speedList.get(4),
                roadList.get(3),
                factoryList.get(4)));

        factoryList.add(createMetalFactory(
                positionList.get(5),
                XMLReader.factoryIdList.get(0),
                speedList.get(3),
                roadList.get(4),
                factoryList.get(4)));

        return factoryList;
    }

    /**
     * Create metal factories
     * This method is implemented to simplify the creation of the three metal factories
     * @param position factory position in the Grid
     * @param id factory id
     * @param speed product speed
     * @param road factory road, from this factory to the client
     * @param client client factory
     * @return MetalFactory
     */
    private MetalFactory createMetalFactory(Position position, int id, Speed speed, Road road,Factory client) {
        return( new MetalFactory(
                XMLReader.factoryIconList.get(0).get(0),
                XMLReader.factoryIconList.get(0).get(1),
                XMLReader.factoryIconList.get(0).get(2),
                XMLReader.factoryIconList.get(0).get(3),
                position,
                id,
                XMLReader.factoryInputQuantityList.get(0),
                XMLReader.factoryProductionTimeList.get(0),
                speed,
                road,
                client
        ));

    }

}