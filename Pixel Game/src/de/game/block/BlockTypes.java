package de.game.block;

public class BlockTypes {
	static int Type_Air = BlockID.air;
	static int Type_bedrock = BlockID.bedrock;
	static int Type_dirt = BlockID.dirt;
	static int Type_grass = BlockID.grass;
	
	public boolean isBreakable(){return true;}
	
	public boolean isNotBreakable(){return false;}
	
	public BlockID GetBlockID(BlockID id){
		
		return id;
	}
	
}
