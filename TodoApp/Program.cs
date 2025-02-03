using System;
using System.Collections.Generic;
using System.IO;
using System.Text.Json;

class Program
{
    static List<TaskItem> tasks = new List<TaskItem>();
    static int taskIdCounter = 1;
    const string filePath = "tasks.json"; 

    static void Main()
    {
        LoadTasks(); // Loads tasks from file on startup
        
        while (true)
        {
            Console.WriteLine("\nTo-Do List Menu:");
            Console.WriteLine("1. Add a Task");
            Console.WriteLine("2. View Tasks");
            Console.WriteLine("3. Remove a Task");
            Console.WriteLine("4. Mark Task as Completed");
            Console.WriteLine("5. Exit");
            Console.Write("Enter your choice: ");

            string input = Console.ReadLine();
            switch (input)
            {
                case "1":
                    AddTask();
                    break;
                case "2":
                    ViewTasks();
                    break;
                case "3":
                    RemoveTask();
                    break;
                case "4":
                    MarkTaskAsCompleted();
                    break;
                case "5":
                    SaveTasks(); // This will save tasks before exiting
                    Console.WriteLine("Exiting... Your tasks have been saved.");
                    return;
                default:
                    Console.WriteLine("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }

    static void AddTask()
    {
        Console.Write("Enter task description: ");
        string description = Console.ReadLine();

        if (string.IsNullOrWhiteSpace(description))
        {
            Console.WriteLine("Task description cannot be empty.");
            return;
        }

        tasks.Add(new TaskItem(taskIdCounter++, description));
        Console.WriteLine("Task added successfully.");
        SaveTasks(); // This will save after adding a task
    }

    static void ViewTasks()
    {
        if (tasks.Count == 0)
        {
            Console.WriteLine("No tasks available.");
            return;
        }

        Console.WriteLine("\nTask List:");
        foreach (var task in tasks)
        {
            string status = task.IsCompleted ? "[✓]" : "[ ]";
            Console.WriteLine($"{task.Id}. {status} {task.Description}");
        }
    }

    static void RemoveTask()
    {
        Console.Write("Enter task ID to remove: ");
        if (int.TryParse(Console.ReadLine(), out int id))
        {
            TaskItem taskToRemove = tasks.Find(t => t.Id == id);
            if (taskToRemove != null)
            {
                tasks.Remove(taskToRemove);
                Console.WriteLine("Task removed successfully.");
                SaveTasks(); // This will save after removing a task
            }
            else
            {
                Console.WriteLine("Task ID not found.");
            }
        }
        else
        {
            Console.WriteLine("Invalid input. Please enter a valid task ID.");
        }
    }

    static void MarkTaskAsCompleted()
    {
        Console.Write("Enter task ID to mark as completed: ");
        if (int.TryParse(Console.ReadLine(), out int id))
        {
            TaskItem task = tasks.Find(t => t.Id == id);
            if (task != null)
            {
                task.IsCompleted = true;
                Console.WriteLine("Task marked as completed.");
                SaveTasks(); // This will save after marking a task as completed
            }
            else
            {
                Console.WriteLine("Task ID not found.");
            }
        }
        else
        {
            Console.WriteLine("Invalid input. Please enter a valid task ID.");
        }
    }

    static void SaveTasks()
    {
        string json = JsonSerializer.Serialize(tasks);
        File.WriteAllText(filePath, json);
    }

    static void LoadTasks()
    {
        if (File.Exists(filePath))
        {
            string json = File.ReadAllText(filePath);
            tasks = JsonSerializer.Deserialize<List<TaskItem>>(json) ?? new List<TaskItem>();
            taskIdCounter = tasks.Count > 0 ? tasks[^1].Id + 1 : 1;
        }
    }
}

class TaskItem
{
    public int Id { get; set; }
    public string Description { get; set; }
    public bool IsCompleted { get; set; }

    public TaskItem(int id, string description)
    {
        Id = id;
        Description = description;
        IsCompleted = false;
    }
}
