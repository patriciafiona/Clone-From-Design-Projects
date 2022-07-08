import 'package:flutter/material.dart';
import 'package:interview/view/DetailPage.dart';

class HomePage extends StatelessWidget {
  static const route_name = "/home-page";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Interview Demo'),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.symmetric(vertical:15.0, horizontal:20.0),
        child: Container(
          width: double.infinity,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Text('This is first Page'),
              ElevatedButton(
                  onPressed: () => Navigator.pushNamed(context, DetailPage.route_name),
                  child: Text(' Go to Detail Page')
              )
            ],
          ),
        ),
      ),
    );
  }
}
