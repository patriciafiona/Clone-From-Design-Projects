import 'package:flutter/material.dart';

class PlaylistItem extends StatelessWidget {
  final Map<String, dynamic> playlist;

  const PlaylistItem({required this.playlist});

  @override
  Widget build(BuildContext context) {
    final String description = playlist['description'] as String;
    final String imageUrl = playlist['imageUrl'] as String;
    final int likes = playlist['likes'] as int;
    final String name = playlist['name'] as String;
    final String singer = playlist['singer'] as String;

    return Container(
      width: 150,
      padding: EdgeInsets.only(right: 15),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Image.network(imageUrl, fit: BoxFit.cover,),
          SizedBox(height: 5,),
          Text(
            name,
            overflow: TextOverflow.ellipsis,
            style: TextStyle(
                fontFamily: 'Gotham',
                fontWeight: FontWeight.bold,
                fontSize: 14,
                color: Colors.white
            ),
          ),
          Text(
            description,
            overflow: TextOverflow.ellipsis,
            style: TextStyle(
                fontFamily: 'Gotham',
                fontWeight: FontWeight.bold,
                fontSize: 14,
                color: Colors.grey
            ),
          ),
        ],
      ),
    );
  }
}
