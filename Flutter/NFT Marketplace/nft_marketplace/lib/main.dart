import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:nft_marketplace/screen/Login/LoginScreen.dart';
import 'package:nft_marketplace/screen/Main/MainScreen.dart';
import 'package:nft_marketplace/screen/Splash/SplashScreen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(
        const SystemUiOverlayStyle(
            statusBarColor: Colors.transparent
        )
    );

    const int blackPrimaryValue = 0xFF2f2f2f;
    const MaterialColor primaryBlack = MaterialColor(
      blackPrimaryValue,
      <int, Color>{
        50: Color(0xFF979797),
        100: Color(0xFF828282),
        200: Color(0xFF6d6d6d),
        300: Color(0xFF595959),
        400: Color(0xFF444444),
        500: Color(blackPrimaryValue),
        600: Color(0xFF2a2a2a),
        700: Color(0xFF262626),
        800: Color(0xFF212121),
        900: Color(0xFF1c1c1c),
      },
    );

    return MaterialApp(
      title: 'NFT Marketplace',
      theme: ThemeData(
        primarySwatch: primaryBlack,
        fontFamily: "FredokaOne",
        textTheme: ThemeData.light().textTheme.copyWith(
          headline6: const TextStyle(
              fontFamily: "FredokaOne",
              fontWeight: FontWeight.bold,
              fontSize: 22,
              color: Colors.white),
        )
      ),
      home: const SplashScreen(),
      routes: {
        LoginScreen.routeName: (ctx) => const LoginScreen(),
        MainScreen.routeName: (ctx) => const MainScreen(),
      },
    );
  }
}