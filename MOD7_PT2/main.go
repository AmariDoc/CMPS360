package main

import (
	"io"
	"net/http"
	"os"
)

func main() {
	http.HandleFunc("/", Handler)
	http.ListenAndServe(":3000", nil)
}

func Handler(w http.ResponseWriter, r *http.Request) {

	f, err := os.Open("./juicewrld.txt")
	if err != nil {
		http.Error(w, "File not found", http.StatusNotFound)
		return
	}
	defer f.Close()

	io.Copy(w, f)
}
