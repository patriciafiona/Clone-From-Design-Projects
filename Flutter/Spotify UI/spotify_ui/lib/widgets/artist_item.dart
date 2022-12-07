import 'package:flutter/material.dart';

class ArtistItem extends StatelessWidget {
  final Map<String, dynamic> data;

  const ArtistItem({required this.data});

  @override
  Widget build(BuildContext context) {
    final String imageUrl = data['imageUrl'] as String;
    final String name = data['name'] as String;

    NetworkImage loadImageNetwork(){
      return NetworkImage(imageUrl);
    }

    return Container(
      width: 200,
      padding: EdgeInsets.only(right: 15),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          CircleAvatar(
          radius: 100.0,
          backgroundImage: loadImageNetwork(),
            backgroundColor: Colors.transparent,
          ),
          SizedBox(height: 5,),
          Container(
            width: double.infinity,
            child: Text(
              name,
              overflow: TextOverflow.ellipsis,
              style: TextStyle(
                  fontFamily: 'Gotham',
                  fontWeight: FontWeight.bold,
                  fontSize: 14,
                  color: Colors.white
              ),
              textAlign: TextAlign.center,
            ),
          ),
        ],
      ),
    );
  }
}
