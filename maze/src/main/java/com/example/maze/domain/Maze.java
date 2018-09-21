package com.example.maze.domain;


public class Maze 
{
	private int witdh;
	private int height ;
	private Cell[][] grid;
	private Coordinate start;
	private String maze;
	
	public Maze()
	{

	}

	public int getWitdh() {
		return witdh;
	}

	public void setWitdh(int witdh) {
		this.witdh = witdh;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}

	public Coordinate getStart() {
		return start;
	}

	public void setStart(Coordinate start) {
		this.start = start;
	}

	public String getMaze() {
		return maze;
	}

	public void setMaze(String maze) {
		this.maze = maze;
	}
	
}
