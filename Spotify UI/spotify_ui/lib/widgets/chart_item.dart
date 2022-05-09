import 'package:flutter/material.dart';

class ChartItem extends StatelessWidget {
  final Map<String, dynamic> chart;

  const ChartItem({required this.chart});

  @override
  Widget build(BuildContext context) {
    final String description = chart['description'] as String;
    final String imageUrl = chart['imageUrl'] as String;
    final int likes = chart['likes'] as int;
    final String name = chart['name'] as String;
    final String type = chart['type'] as String;

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
