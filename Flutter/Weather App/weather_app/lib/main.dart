import 'package:flutter/material.dart';
import 'package:weather_app/screens/home/HomeScreen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Weather App',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: const Color.fromARGB(255, 16, 31, 55)),
        useMaterial3: true,
      ),
      home: const HomeScreen(),
    );
  }
}
