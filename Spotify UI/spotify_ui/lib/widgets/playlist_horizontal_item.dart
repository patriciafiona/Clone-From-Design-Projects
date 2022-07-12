import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:spotify_ui/screens/playlist_screen/playlist_screen.dart';

class PlaylistHorizontalItem extends StatelessWidget {
  final Map<String, dynamic> playlist;

  const PlaylistHorizontalItem({required this.playlist});

  @override
  Widget build(BuildContext context) {
    final String description = playlist['description'] as String;
    final String imageUrl = playlist['imageUrl'] as String;
    final String backgroundUrl = playlist['backgroundUrl'] as String;
    final int likes = playlist['likes'] as int;
    final int followers = playlist['followers'] as int;
    final String name = playlist['name'] as String;
    final String singer = playlist['singer'] as String;
    final String type = playlist['type'] as String;

    return Container(
      width: 150,
      padding: EdgeInsets.only(right: 15),
      child: TextButton(
            onPressed: (){
              if(type == 'playlist'){
                //get the list of songs in the database based on singer
                Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => PlaylistScreen(
                        backgroundUrl: backgroundUrl,
                        name: name,
                        description: description,
                        likes: likes,
                        followers: followers,
                        singer: singer)
                    )
                );
              }
            },
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                SizedBox(
                  width: 100,
                  child: FadeInImage(
                    placeholder: AssetImage('assets/images/placeholder.png'),
                    image: NetworkImage(
                      imageUrl,
                    ),
                    fit: BoxFit.cover,
                  ),
                ),
                SizedBox(width: 15,),
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        name,
                        overflow: TextOverflow.ellipsis,
                        style: TextStyle(
                            fontFamily: 'Gotham',
                            fontWeight: FontWeight.bold,
                            fontSize: 20,
                            color: Colors.white
                        ),
                      ),
                      SizedBox(height: 10),
                      Text(
                        "by Spotify",
                        overflow: TextOverflow.ellipsis,
                        style: TextStyle(
                            fontFamily: 'Gotham',
                            fontWeight: FontWeight.bold,
                            fontSize: 16,
                            color: Colors.grey
                        ),
                      ),
                    ],
                  )
                ),
              ],
            ),
          )
    );
  }
}
