import 'package:flutter/material.dart';

class PodcastTabScreen extends StatelessWidget {
  const PodcastTabScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);
    
    return Center(
      child: SizedBox(
        width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.85,
        child: Image.asset("assets/images/under_construction.png"),
      ),
    );
  }
}
