import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:spotify_ui/screens/auth/auth_screen.dart';
import 'package:spotify_ui/screens/main/main_screen.dart';
import 'package:spotify_ui/screens/settings/settings_screen.dart';
import 'package:spotify_ui/screens/splash/splash_screen.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  SystemChrome.setPreferredOrientations([
    DeviceOrientation.portraitUp,
    DeviceOrientation.portraitUp,
  ]);
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    final Future<FirebaseApp> _initialization = Firebase.initializeApp();

    const int _blackPrimaryValue = 0xFF2f2f2f;
    const MaterialColor primaryBlack = MaterialColor(
      _blackPrimaryValue,
      <int, Color>{
        50: Color(0xFF979797),
        100: Color(0xFF828282),
        200: Color(0xFF6d6d6d),
        300: Color(0xFF595959),
        400: Color(0xFF444444),
        500: Color(_blackPrimaryValue),
        600: Color(0xFF2a2a2a),
        700: Color(0xFF262626),
        800: Color(0xFF212121),
        900: Color(0xFF1c1c1c),
      },
    );

    return FutureBuilder(
      // Initialize FlutterFire:
        future: _initialization,
        builder: (context, appSnapshot) {
          return MaterialApp(
            title: 'Spotify',
            theme: ThemeData(
                primarySwatch: primaryBlack,
                fontFamily: 'Gotham',
                textTheme: ThemeData.light().textTheme.copyWith(
                  headline6: const TextStyle(
                      fontFamily: 'Gotham',
                      fontWeight: FontWeight.bold,
                      fontSize: 22,
                      color: Colors.white),
                )
            ),
            home: appSnapshot.connectionState != ConnectionState.done ? const SplashScreen() :
            StreamBuilder(stream: FirebaseAuth.instance.authStateChanges(), builder: (ctx, userSnapshot) {
              if (userSnapshot.connectionState == ConnectionState.waiting) {
                return const SplashScreen();
              }
              if (userSnapshot.hasData) {
                return const MainScreen();
              }
              return const AuthScreen();
            }),
            routes: {
              SettingsScreen.routeName: (ctx) => SettingsScreen(),
              AuthScreen.routeName: (ctx) => AuthScreen(),
            },
          );
        });
  }
}