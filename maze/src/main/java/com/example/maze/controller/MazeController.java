package com.example.maze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.maze.domain.Maze;
import com.example.maze.service.MazeDaoImpl;


@RestController
public class MazeController 
{
	@Autowired
	MazeDaoImpl repo;
	
	
	@GetMapping("/create_maze")
	public Maze createMaze()
	{
		return repo.generateMaze();
	}
	
}
