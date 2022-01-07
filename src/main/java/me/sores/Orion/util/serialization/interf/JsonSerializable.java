package me.sores.Orion.util.serialization.interf;


import me.sores.Orion.util.json.JSONObject;

/**
 * Created by LavaisWatery on 5/13/2017.
 */
public interface JsonSerializable {

    public JSONObject serialize();

    public void deserialize(JSONObject object);

}
