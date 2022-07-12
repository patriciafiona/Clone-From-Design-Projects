import 'package:flutter/material.dart';

class ArtistHorizontalItem extends StatelessWidget {
  final Map<String, dynamic> data;

  const ArtistHorizontalItem({required this.data});

  @override
  Widget build(BuildContext context) {
    final String imageUrl = data['imageUrl'] as String;
    final String name = data['name'] as String;

    NetworkImage loadImageNetwork(){
      return NetworkImage(imageUrl);
    }

    return Container(
      width: 200,
      padding: EdgeInsets.only(bottom: 10, top: 10, right: 15, left: 15),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          CircleAvatar(
            radius: 50.0,
            backgroundImage: loadImageNetwork(),
            backgroundColor: Colors.transparent,
          ),
          SizedBox(width: 15),
          Expanded(
            child: Text(
              name,
              overflow: TextOverflow.ellipsis,
              style: TextStyle(
                  fontFamily: 'Gotham',
                  fontWeight: FontWeight.bold,
                  fontSize: 18,
                  color: Colors.white
              ),
              textAlign: TextAlign.start,
            ),
          ),
        ],
      ),
    );
  }
}
