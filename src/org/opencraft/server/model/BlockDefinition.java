package org.opencraft.server.model;

/*
 * OpenCraft License
 * 
 * Copyright (c) 2009 Graham Edgecombe.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *       
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *       
 *     * Neither the name of the OpenCraft nor the names of its
 *       contributors may be used to endorse or promote products derived from
 *       this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

import org.opencraft.server.model.BlockBehaviour;


/**
 * Represents an individual block type.
 * @author Brett Russell
 *
 */
public class BlockDefinition {
	
	/**
	 * A method that exists for legacy reasons.
	 * @param id The block id.
	 * @return The block definition.
	 */
	public static BlockDefinition forId(int id) {
		return BlockManager.getBlockManager().getBlock(id);
	}
	
	/**
	 * The block name.
	 */
	private String name;
	
	/**
	 * The block ID.
	 */
	private int bid;
	
	/**
	 * The block's solidity.
	 */
	private boolean solid;
	
	/**
	 * The block's fluidity.
	 */
	private boolean liquid;
	
	/**
	 * The block's transparency.
	 */
	private boolean blocksLight;
	
	/**
	 * The block's size.
	 */
	private boolean halfBlock;
	
	/**
	 * The block's behaviour, as a string.
	 */
	private String behaviourName;
	
	/**
	 * The block's behaviour.
	 */
	private transient BlockBehaviour behaviour;
	
	/**
	 * The block's periodic physics check state.
	 */
	private boolean doesThink;
	
	/**
	 * The timer, in milliseconds, on which this block thinks.
	 */
	private long thinkTimer;
	
	/**
	 * Constructor.
	 */
	BlockDefinition(String name, int bid, boolean solid, boolean liquid, boolean blocksLight, boolean halfBlock, boolean doesThink, long thinkTimer, String behaviourName) {
		this.name = name;
		this.bid = bid;
		this.solid = solid;
		this.liquid = liquid;
		this.blocksLight = blocksLight;
		this.halfBlock = halfBlock;
		this.doesThink = doesThink;
		this.thinkTimer = thinkTimer;
		this.behaviourName = behaviourName;
		
		/*
		try{
			this.behaviour = (BlockBehaviour) Class.forName(this.behaviourName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	/**
	 * Resolves this object.
	 * @return A resolved object.
	 */
	private Object readResolve() {
		return new BlockDefinition(name, bid, solid, liquid, blocksLight, halfBlock, doesThink, thinkTimer, behaviourName);
	}
	
	/**
	 * Gets the name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the ID.
	 * @return The ID.
	 */
	public int getId() {
		return bid;
	}

	/**
	 * Gets the solidity.
	 * @return The solidity.
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * Gets the fluidity.
	 * @return The fluidity.
	 */
	public boolean isLiquid() {
		return liquid;
	}

	/**
	 * Gets the transparency.
	 * @return The transparency.
	 */
	public boolean isBlocksLight() {
		return blocksLight;
	}

	/**
	 * Gets the size.
	 * @return The size.
	 */
	public boolean isHalfBlock() {
		return halfBlock;
	}

	/**
	 * Gets the periodic physics check state.
	 * @return The... yeah.
	 */
	public boolean doesThink() {
		return doesThink;
	}
	
	/**
	 * Apply passive physics.
	 * @param level
	 * @param x
	 * @param y
	 * @param z
	 * @param type
	 */
	public void behavePassive(Level level, int x, int y, int z) {
		this.behaviour.handlePassive(level, x, y, z, this.bid);
	}
	
	/**
	 * Apply physics on block destruction.
	 * @param level
	 * @param x
	 * @param y
	 * @param z
	 * @param type
	 */
	public void behaveDestruct(Level level, int x, int y, int z) {
		this.behaviour.handleDestroy(level, x, y, z, this.bid);
	}
	
	/**
	 * Apply periodic physics.
	 * @param level
	 * @param x
	 * @param y
	 * @param z
	 * @param type
	 */
	public void behaveSchedule(Level level, int x, int y, int z) {
		this.behaviour.handleScheduledBehaviour(level, x, y, z, this.bid);
	}
	
	public long getTimer() {
		return thinkTimer;
	}
}
	

