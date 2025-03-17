package main

import (
	"fmt"
	"log"
	"net/http"
)

func homeHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "<h1>Welcome to My Simple Go Website</h1>")
	fmt.Fprint(w, `<p><a href="/about">About Me</a></p>`)
	fmt.Fprint(w, `<p><a href="/hobby">My Hobby</a></p>`)
}

func aboutHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "<h1>About Me</h1>")
	fmt.Fprint(w, `<img src="/static/about.jpg" alt="About Me" width="300">`)
	fmt.Fprint(w, "<p>Hello! My name is Amari! Im working towards a bachelor degree !</p>")
}

func hobbyHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "<h1>My Hobby</h1>")
	fmt.Fprint(w, `<img src="/static/hobby.jpg" alt="My Hobby" width="300">`)
	fmt.Fprint(w, "<p>One of my favorite hobbies is watching my brothers play football.</p>")
}

func main() {
	http.HandleFunc("/", homeHandler)
	http.HandleFunc("/about", aboutHandler)
	http.HandleFunc("/hobby", hobbyHandler)

	// Serve static files like images
	fs := http.FileServer(http.Dir("static"))
	http.Handle("/static/", http.StripPrefix("/static/", fs))

	log.Println("Server is running on http://localhost:8080")
	http.ListenAndServe(":8080", nil)
}
