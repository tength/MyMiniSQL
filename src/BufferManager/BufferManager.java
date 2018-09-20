package BufferManager;

import javafx.collections.transformation.SortedList;

import java.util.HashMap;

public class BufferManager {
    //todo
    public static void initialize(){}
    public static void close(){}

    /**
     * get the block which is on the blockOffset in the file, first find in the block pool in memory, and
     *  get the block from the disk if it is not in memory.
     * @param fileName file name
     * @param blockOffset block offset
     * @return the asked block
     */
    public static Block getBlock(String fileName, int blockOffset){
        return null;
    }

    /**
     * drop blocks bind with the file
     * @param fileName file name
     */
    public static void dropBlocks(String fileName){

    }

}

