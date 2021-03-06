package net.gtaun.shoebill.pickup;

import net.gtaun.shoebill.DynamicObjectPool;
import net.gtaun.shoebill.Streamer;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.data.Updateable;
import net.gtaun.shoebill.object.Destroyable;

import java.util.ArrayList;
import java.util.Collection;

// Created by marvin on 28.12.14 in project shoebill-streamer.
// Copyright (c) 2014 Marvin Haschker. All rights reserved.
@SuppressWarnings("UnusedDeclaration")
public interface DynamicPickup extends Destroyable, Updateable {
    int INVALID_ID = -1;
    DynamicObjectPool<DynamicPickup> objectPool = new DynamicObjectPool<>();

    /**
     * Creates a dynamic samp pickup.
     * @param modelid The modelid of the pickup (see: http://wiki.sa-mp.com/wiki/Pickup_IDs)
     * @param type The type of the pickup (see: http://wiki.sa-mp.com/wiki/PickupTypes)
     * @param location The location where the pickup should be (x, y, z, worldid, interiorid)
     * @param streamDistance The distance when the pickup should be visible (viewDistance)
     * @return The created pickup
     */
    static DynamicPickup create(int modelid, int type, Location location, float streamDistance) {
        if(!Streamer.isInitialized) {
            System.err.println("Please insert the Streamer into your plugins section in resources.yml");
            return null;
        } else {
            return new DynamicPickupImpl(modelid, type, location, streamDistance);
        }
    }

    /**
     * Creates a dynamic samp pickup.
     * @param modelid The modelid of the pickup (see: http://wiki.sa-mp.com/wiki/Pickup_IDs)
     * @param type The type of the pickup (see: http://wiki.sa-mp.com/wiki/PickupTypes)
     * @param x X-Cord
     * @param y Y-Cord
     * @param z Z-Cord
     * @param worldId The world id where the pickup should be
     * @param interiorId The interior where the pickup should be
     * @param streamDistance The distance when the pickup should be visible (viewDistance)
     * @return The created pickup
     */
    static DynamicPickup create(int modelid, int type, float x, float y, float z, int worldId, int interiorId, float streamDistance) {
        return create(modelid, type, new Location(x, y, z, interiorId, worldId), streamDistance);
    }

    static DynamicPickup get(int id) {
        return DynamicPickupImpl.get(id);
    }

    static Collection<DynamicPickup> get() {
        return new ArrayList<>(objectPool.getAllObjects());
    }

    static void destroyAll() {
        DynamicPickupImpl.destroyAll();
    }

    int getId();

    Location getLocation();
    void setLocation(Location location);

    int getType();
    void setType(int type);

    int getModelId();
    void setModelId(int modelId);

    float getStreamDistance();
    void setStreamDistance(float streamDistance);
}
