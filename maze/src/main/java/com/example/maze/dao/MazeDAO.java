package com.example.maze.dao;

import com.example.maze.domain.Maze;

public interface MazeDAO 
{
	public Maze generateMaze();	
	public Maze putMove(Maze maze);
}

