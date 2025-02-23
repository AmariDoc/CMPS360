package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	reader := bufio.NewReader(os.Stdin)

	fmt.Println("What is your favorite Juice WRLD song?")
	song, _ := reader.ReadString('\n')
	song = strings.TrimSpace(song)

	fmt.Println("What is your favorite Juice WRLD album?")
	album, _ := reader.ReadString('\n')
	album = strings.TrimSpace(album)

	fmt.Println("What is a memorable lyric from Juice WRLD's music?")
	lyric, _ := reader.ReadString('\n')
	lyric = strings.TrimSpace(lyric)

	fmt.Println("\n--- Your Music Preferences ---")
	fmt.Printf("Favorite Song: %s\n", strings.ToUpper(song))
	fmt.Printf("Favorite Album: %s\n", strings.ToUpper(album))
	fmt.Printf("Memorable Lyric: \"%s\"\n", lyric)
}
