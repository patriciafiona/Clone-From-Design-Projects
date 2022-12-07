import 'package:flutter/material.dart';

class AlbumHorizontalItem extends StatelessWidget {
  final Map<String, dynamic> data;

  const AlbumHorizontalItem({required this.data});

  @override
  Widget build(BuildContext context) {
    final String imageUrl = data['imageUrl'] as String;
    final String name = data['name'] as String;

    return Container(
      width: 200,
      padding: EdgeInsets.only(bottom: 10, top: 10, right: 15, left: 15),
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
