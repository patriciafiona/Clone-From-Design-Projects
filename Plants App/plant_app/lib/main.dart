import 'package:flutter/material.dart';
import 'package:plant_app/screen/BottomNavigation/BottomNavigationScreen.dart';
import 'package:plant_app/screen/WelcomeScreen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Planto Shop',
      theme: ThemeData(
          primarySwatch: Colors.grey,
          fontFamily: 'Aller',
          textTheme: ThemeData.light().textTheme.copyWith(
            headline6: const TextStyle(
                fontFamily: 'Aller',
                fontSize: 22,
                color: Colors.black),
          )
      ),
      home: const WelcomeScreen(),
      routes: {
        WelcomeScreen.routeName: (ctx) => const WelcomeScreen(),
        BottomNavigationScreen.routeName: (ctx) => const BottomNavigationScreen(),
      },
    );
  }
}
