package com.example.maze.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.example.maze.dao.MazeDAO;
import com.example.maze.domain.Cell;
import com.example.maze.domain.Coordinate;
import com.example.maze.domain.Maze;



@Repository
public class MazeDaoImpl implements MazeDAO
{
	public Maze generateMaze() 
	{
		int height = (int)(Math.random()*5)+15;
		int witdh= (int )(Math.random()*5)+15;
		
		int sHeight = (int)(Math.random()*14)+1;
		int sWitdh= (int )(Math.random()*14)+1;
		
		int eHeight = (int)(Math.random()*14)+1;
		int eWitdh= (int )(Math.random()*14)+1;

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
		Coordinate end = new Coordinate(eWitdh,eHeight);
		
		Maze maze = new Maze();
		maze.setGrid(grid);
		maze.setHeight(height);
		maze.setStart(start);
		maze.setWitdh(witdh);
		
		dig(start.getX(),start.getY(),maze);
		String m="";
		grid[start.getX()][start.getY()].setValue("S");
		
		do
		{
			eHeight = (int)(Math.random()*14)+1;
			eWitdh= (int )(Math.random()*14)+1;
			end = new Coordinate(eWitdh,eHeight);
			if(grid[eWitdh][eHeight].getValue().equals(" "))
			{
				end.setValue("F");
				grid[eWitdh][eHeight].setValue("F");
			}
				
		}while (end.getValue()==null);
		
		for (int x=0; x< witdh; x++)
		{
			for(int y=0; y < height; y++)
			{
				if(grid[x][y].getValue().equals("O"))
					grid[x][y].setValue("X");
				m = m+grid[x][y].getValue();
			}
			m= m+"\n";
		}

					
		maze.setMaze(m);
		maze.setCurrent(start);
		maze.setPath(Arrays.copyOf(grid, grid.length));
		maze.setsPath(m);
		maze.setPosition(Arrays.copyOf(grid, grid.length));
		return maze;
	}

	@Override
	public Maze putMove(Maze maze)
	{
		String move = maze.getMove();
		Coordinate current = maze.getCurrent();
		Coordinate request = null;
		int x = current.getX();
		int y = current.getY();
		int witdh= maze.getWitdh();
		int height = maze.getHeight();
		Cell[][] grid = maze.getPath();
		if(move.equals("up"))
		{
			if(isValid(x-1,y,witdh,height,grid) )
			{
				if(!isWall(x-1,y,grid))
				{
					current= new Coordinate(x-1,y,getObject(x-1,y,grid));
					request = new Coordinate(x-1,y,getObject(x-1,y,maze.getGrid()));
					grid[x][y].setValue("*");
					grid[x-1][y].setValue("°");
					maze.setsPath(generateStringMaze(witdh,height,grid));
					maze.setPath(grid);
				}
				else 
					request = new Coordinate(x-1,y,getObject(x-1,y,maze.getGrid()));
			}
			else 
				request = new Coordinate(x-1,y,"OUT OF MAZE");
		}
		else if(move.equals("down"))
		{
			if(isValid(x+1,y,witdh,height,grid))
			{
				if(!isWall(x+1,y,grid))
				{
					current= new Coordinate(x+1,y,getObject(x+1,y,grid));
					grid[x][y].setValue("*");
					grid[x+1][y].setValue("°");
					maze.setsPath(generateStringMaze(witdh,height,grid));
					request = new Coordinate(x+1,y,getObject(x+1,y,maze.getGrid()));
					maze.setPath(grid);
				}
				else 
					request = new Coordinate(x+1,y,getObject(x+1,y,maze.getGrid()));
			}
			else 
				request = new Coordinate(x+1,y,"OUT OF MAZE");
		}
		else if(move.equals("left"))
		{
			if(isValid(x, y-1,witdh,height, grid))
			{
				if(!isWall(x,y-1,grid))
				{
					current = new Coordinate(x,y-1,getObject(x,y-1,grid));
					grid[x][y].setValue("*");
					grid[x][y-1].setValue("°");
					maze.setsPath(generateStringMaze(witdh,height,grid));
					request = new Coordinate(x,y-1,getObject(x,y-1,maze.getGrid()));
					maze.setPath(grid);
				}
				else 
					request = new Coordinate(x,y-1,getObject(x,y-1,maze.getGrid()));
			}
			else 
				request = new Coordinate(x,y-1,"OUT OF MAZE");
		}
		else if(move.equals("right"))
		{
			if(isValid(x,y+1,witdh,height,grid))
			{
				if(!isWall(x,y+1,grid))
				{
					current = new Coordinate(x,y+1,getObject(x,y+1,grid));
					grid[x][y].setValue("*");
					grid[x][y+1].setValue("°");
					maze.setsPath(generateStringMaze(witdh,height,grid));
					request = new Coordinate(x,y+1,getObject(x,y+1,maze.getGrid()));
					maze.setPath(grid);
				}
				else 
					request = new Coordinate(x,y+1,getObject(x,y+1,maze.getGrid()));
			}
			else 
				request = new Coordinate(x,y+1,"OUT OF MAZE");
		}
			
		maze.setRequest(request);
		maze.setCurrent(current);
		
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
	private boolean isValid(int x, int y, int witdh, int height, Cell[][]grid)
	{
		if(x > 0 && x < witdh && y >0 && y < height )
		{
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
	private String getObject(int x,int y, Cell[][]grid)
	{
		String key = grid[x][y].getValue();
		switch (key)
		{
			case "X":
				return "WALL";
			case " ":
				return "SPACE";
			case "S":
				return "ENTRY";
			case "F":
				return "EXIT";
		}
		return "NONE";
	}
	private String generateStringMaze(int witdh,int height,Cell[][]grid)
	{
		String m="";
		for (int x=0; x< witdh; x++)
		{
			for(int y=0; y < height; y++)
			{
				m = m+grid[x][y].getValue();
			}
			m= m+"\n";
		}
		return m;
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
