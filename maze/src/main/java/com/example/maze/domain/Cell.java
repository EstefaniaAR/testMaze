package com.example.maze.domain;

public class Cell 
{
	private boolean visited;
	private boolean carved;
	private String value;
	
	private Coordinate before;
	private Coordinate next;
	
	public Cell ()
	{
		this.value="O";
		this.visited=false;
		this.carved=false;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isCarved() {
		return carved;
	}

	public void setCarved(boolean carved) {
		this.carved = carved;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Coordinate getBefore() {
		return before;
	}

	public void setBefore(Coordinate before) {
		this.before = before;
	}

	public Coordinate getNext() {
		return next;
	}

	public void setNext(Coordinate next) {
		this.next = next;
	}
	
	
}
