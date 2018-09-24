package com.example.maze.domain;

public class Coordinate
{
	private int x;
	private int y;
	private String value;
	
	public Coordinate()
	{
		
	}
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinate(int x, int y, String value) {
		super();
		this.x = x;
		this.y = y;
		this.value = value;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + ", value=" + value + "]";
	}
	
}
