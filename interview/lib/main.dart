import 'package:flutter/material.dart';
import 'package:interview/view/DetailPage.dart';
import 'package:interview/view/HomePage.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Interview Test',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        fontFamily: 'Gotham',
      ),
      home: HomePage(),
      routes: {
        HomePage.route_name: (ctx) => HomePage(),
        DetailPage.route_name: (ctx) => DetailPage(),
      },
    );
  }
}
