import 'package:flutter/material.dart';

class LastSeenItem extends StatelessWidget {
  final Map<String, dynamic> data;

  const LastSeenItem({required this.data});

  @override
  Widget build(BuildContext context) {
    final String description = data['description'] == null ? '' : data['description'] as String;
    final String imageUrl = data['imageUrl'] as String;
    final String type = data['type'] as String;
    final String name = data['name'] as String;

    NetworkImage loadImageNetwork(){
      return NetworkImage(imageUrl);
    }

    return Container(
      width: 150,
      padding: EdgeInsets.only(right: 15),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          type == 'artist' ?
          CircleAvatar(
          radius: 75.0,
          backgroundImage: loadImageNetwork(),
            backgroundColor: Colors.transparent,
          ) :
          FadeInImage(
            placeholder: AssetImage('assets/images/placeholder.png'),
            image: loadImageNetwork(),
            fit: BoxFit.cover,
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
              textAlign: type == 'artist' ? TextAlign.center : TextAlign.start,
            ),
          ),
          description != '' ? Text(
            description,
            overflow: TextOverflow.ellipsis,
            style: TextStyle(
                fontFamily: 'Gotham',
                fontWeight: FontWeight.bold,
                fontSize: 14,
                color: Colors.grey
            ),
          ) : Spacer(),
        ],
      ),
    );
  }
}
