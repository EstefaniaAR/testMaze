package com.example.maze.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.example.maze.domain.Cell;
import com.example.maze.domain.Coordinate;
import com.example.maze.domain.Maze;



@Repository
public class MazeDaoImpl implements com.example.maze.dao.MazeDAO
{
	public Maze generateMaze() 
	{
		int height = (int)(Math.random()*5)+15;
		int witdh= (int )(Math.random()*5)+15;
		
		int sHeight = (int)(Math.random()*14)+1;
		int sWitdh= (int )(Math.random()*14)+1;

		Cell [][] grid= new Cell [witdh][height];

		for (int x=0; x< witdh; x++)
		{
			for(int y=0; y < height; y++)
			{
				grid[x][y]=new Cell();
				if(y==0 && x < grid.length-1)
					grid[x][y].setValue("X");
				else if(x==0 && y!=0)
					grid[x][y].setValue("X");
				else if(x==0 && y==grid[x].length-1)
					grid[x][y].setValue("X");
				else if(x==grid.length-1)
					grid[x][y].setValue("X");
				else if(y==grid[x].length-1)
					grid[x][y].setValue("X");
			}
		}
		
		Coordinate start = new Coordinate(sWitdh,sHeight);
		Maze maze = new Maze();
		maze.setGrid(grid);
		maze.setHeight(height);
		maze.setStart(start);
		maze.setWitdh(witdh);
		
		dig(start.getX(),start.getY(),maze);
		String m="";
		for (int x=0; x< witdh; x++)
		{
			for(int y=0; y < height; y++)
			{
				if(grid[x][y].getValue().equals("O"))
					grid[x][y].setValue("X");
				System.out.print(grid[x][y].getValue());
				m = m+grid[x][y].getValue();
			}
			m= m+"\n";
			System.out.println();
		}
		maze.setMaze(m);
		System.out.println("----");
		System.out.println(m);
		
		return maze;
	}


	/*Recursive Backtracking*/
	private void dig(int x , int y, Maze maze)
	{
		Cell[][] grid = maze.getGrid();
		Coordinate start = maze.getStart();
		int witdh = maze.getWitdh();
		int height = maze.getHeight();
		
		if(!grid[start.getX()][start.getY()].isCarved() && x< witdh && y < height && x>=0 && y>=0 ) 
		{
			Coordinate cor = getNextCoordinate(x,y,maze);
			if(cor!=null)
				dig(cor.getX(),cor.getY(),maze);
		}
	}
	
	private Coordinate getNextCoordinate(int x, int y, Maze maze)
	{

		List<Coordinate>options = new ArrayList<Coordinate>();
		Coordinate target = null;
		Cell[][] grid = maze.getGrid();
		int witdh = maze.getWitdh();
		int height = maze.getHeight();
		//North (x-1,y)
		if(exists(x-1,y,witdh,height,grid) )
		{
			if(!isWall(x-1,y,grid))
				options.add(new Coordinate(x-1,y,"N"));
		}
		//East (x,y+1) 
		if(exists(x,y+1,witdh,height,grid))
		{
			if(!isWall(x,y+1,grid))
					options.add(new Coordinate(x,y+1,"E"));
		}
		//South (x+1,y)
		if(exists(x+1,y,witdh,height,grid))
		{
			if(!isWall(x+1,y,grid))
				options.add( new Coordinate(x+1,y,"S"));
		}
		//West (x,y-1)
		if(exists(x,y-1,witdh,height,grid))
		{
			if(!isWall(x,y-1,grid))
				options.add(new Coordinate(x,y-1,"W"));
		}
		
		if(options.size()>0)
		{

			Random rand = new Random();
			int i = rand.nextInt(options.size());
			target= options.get(i);
			grid[x][y].setNext(target);
			grid[x][y].setVisited(true);
			grid[x][y].setValue(" ");
			
			switch (target.getValue())
			{
				case "N":
					if(isNeedWall(x,y+1,witdh,height,grid))
						grid[x][y+1].setValue("X");
					
					if(isNeedWall(x,y-1,witdh,height,grid))
						grid[x][y-1].setValue("X");
				case "S":
					if(isNeedWall(x,y+1,witdh,height,grid))
						grid[x][y+1].setValue("X");
					
					if(isNeedWall(x,y-1,witdh,height,grid))
						grid[x][y-1].setValue("X");
				case "E":
					if(isNeedWall(x-1,y,witdh,height,grid))
						grid[x-1][y].setValue("X");
					
					if(isNeedWall(x+1,y,witdh,height,grid))
						grid[x+1][y].setValue("X");
				case "W":
					if(isNeedWall(x-1,y,witdh,height,grid))
						grid[x-1][y].setValue("X");
					
					if(isNeedWall(x+1,y,witdh,height,grid))
						grid[x+1][y].setValue("X");
			}
			grid[target.getX()][target.getY()].setBefore(new Coordinate(x,y));
			
		}
		else 
		{
			// Go to te previus coordinate
			if(grid[x][y].getBefore()!=null)
				target = grid[x][y].getBefore();
			grid[x][y].setVisited(true);
			grid[x][y].setCarved(true);
			
		}

		options.clear();
		return target;
	}
	private boolean exists(int x, int y, int witdh, int height, Cell[][]grid)
	{
		if(x > 0 && x < witdh && y >0 && y < height )
		{
			if(!grid[x][y].isVisited())
				return true;
		}
		return false;
	}
	
	private boolean isNeedWall(int x,int y,int witdh, int height, Cell[][]grid)
	{
		if(exists(x,y,witdh,height,grid))
			if(!isBlank(x,y,grid))
				return true;
		return false;
	}
	private boolean isWall(int x,int y, Cell[][]grid)
	{
		if(grid[x][y].getValue().equals("X"))
			return true;
		return false;
	}
	
	private boolean isBlank(int x,int y,Cell[][]grid)
	{
		if(grid[x][y].getValue().equals(" "))
			return true;
		return false;
	}
}
