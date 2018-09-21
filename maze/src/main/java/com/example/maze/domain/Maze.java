package com.example.maze.domain;


public class Maze 
{
	private int witdh;
	private int height ;
	private Cell[][] grid;
	private Cell[][] path;
	private Cell[][] position;
	private Coordinate start;
	private Coordinate end;
	private String maze;
	private Coordinate current;
	private String move;
	
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

	public Coordinate getEnd() {
		return end;
	}

	public void setEnd(Coordinate end) {
		this.end = end;
	}

	public Coordinate getCurrent() {
		return current;
	}

	public void setCurrent(Coordinate current) {
		this.current = current;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public Cell[][] getPath() {
		return path;
	}

	public void setPath(Cell[][] path) {
		this.path = path;
	}

	public Cell[][] getPosition() {
		return position;
	}

	public void setPosition(Cell[][] position) {
		this.position = position;
	}

}
