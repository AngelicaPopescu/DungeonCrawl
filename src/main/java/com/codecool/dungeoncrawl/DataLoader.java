package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.BluePotion;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;
import com.codecool.dungeoncrawl.logic.levels.OpenDoor;
import com.codecool.dungeoncrawl.logic.levels.YellowDoor;
import com.codecool.dungeoncrawl.model.ActorModel;
import com.codecool.dungeoncrawl.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    GameDatabaseManager dbManager;
    GameMap map;
    public DataLoader(GameDatabaseManager dbManager, GameMap map) {
        this.dbManager = dbManager;
        this.map = map;
    }

    public List<Actor> getAllActors(){
        List<ActorModel> actorModels = dbManager.listAllActors("oop");
        List<Actor> actors = new ArrayList<>();
        for(ActorModel actorModel: actorModels) {
            Cell cell = map.getCell(actorModel.getX(), actorModel.getY());
            Actor actor;
            switch (actorModel.getActorName()) {
                case "player":
                    actor = new Player(cell);
                    break;
                case "casper":
                    actor = new Casper(cell);
                    break;
                case "creepyBug":
                    actor = new CreepyBug(cell);
                    break;
                default:
                    actor = new Skeleton(cell);
                    break;
            }
            actor.setHealth(actorModel.getHp());
            actors.add(actor);
        }
        System.out.println(actors);
        return actors;
    }

    public List<Item> getAllItems(){
        List<ItemModel> itemModels = dbManager.listAllItem("oop");
        List<Item> items = new ArrayList<>();
        for(ItemModel itemModel: itemModels) {
            Cell cell = map.getCell(itemModel.getX(), itemModel.getY());
            Item item;
            switch (itemModel.getItemName()) {
                case "sword":
                    item = new Sword(cell);
                    break;
                case "bluePotion":
                    item = new BluePotion(cell);
                    break;
                case "yellowDoor":
                    item = new YellowDoor(cell);
                    break;
                case "openDoor":
                    item = new OpenDoor(cell);
                    break;
                default:
                    item = new Key(cell);
                    break;
            }
            items.add(item);
        }
        System.out.println(items);
        return items;
    }
}