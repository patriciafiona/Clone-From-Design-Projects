import 'package:flutter/material.dart';

class RadioItem extends StatelessWidget {
  final Map<String, dynamic> radio;

  const RadioItem({required this.radio});

  @override
  Widget build(BuildContext context) {
    final String imageUrl = radio['imageUrl'] as String;
    final String name = radio['name'] as String;

    return Container(
      width: 200,
      padding: EdgeInsets.only(right: 15),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          FadeInImage(
            placeholder: AssetImage('assets/images/placeholder.png'),
            image: NetworkImage(
              imageUrl,
            ),
            fit: BoxFit.cover,
          ),
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
            'By Spotify',
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
