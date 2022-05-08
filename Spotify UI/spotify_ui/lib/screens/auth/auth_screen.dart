import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:spotify_ui/screens/main/main_screen.dart';

import '../../authentication/auth.dart';

class AuthScreen extends StatefulWidget {
  const AuthScreen({Key? key}) : super(key: key);

  @override
  State<AuthScreen> createState() => _AuthScreenState();
}

class _AuthScreenState extends State<AuthScreen> {
  bool _isSigningIn = false;

  void _onGoogleSignInBtnClicked() async {
    setState(() {
      _isSigningIn = true;
    });

    User? user = await Authentication.signInWithGoogle(context: context);

    setState(() {
      _isSigningIn = false;
    });

    if (user != null) {
      Navigator.of(context).pushReplacement(
        MaterialPageRoute(
          builder: (context) => MainScreen(),
        ),
      );
    }
  }

  Widget textTemplate(String text){
    return Text(text, style: const TextStyle(
      fontFamily: 'Gotham',
      fontWeight: FontWeight.bold,
      color: Colors.white,
      fontSize: 46,
    ),
      textAlign: TextAlign.start,
    );
  }

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
            gradient: LinearGradient(
              begin: Alignment.topCenter,
              end: Alignment.bottomCenter,
              colors: [
                Color.fromRGBO(64, 64, 64, 1),
                Colors.black87,
              ],
              stops: [
                0.1,
                0.4
              ],
            )
        ),
        child: _isSigningIn ? Center(
          child: CircularProgressIndicator(),
        ):SingleChildScrollView(
          child: Container(
            padding: EdgeInsets.all(20),
            width: double.infinity,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Container(
                  width: 90,
                  height: (mediaQuery.size.height -
                      mediaQuery.padding.top) * 0.1,
                  child: Image.asset(
                    'assets/images/spotify_logo_v5.png',
                  ),
                ),
                SizedBox(height: (mediaQuery.size.height - mediaQuery.padding.top) * 0.51),
                textTemplate('Millions of songs.'),
                SizedBox(height: 10,),
                textTemplate('Free on Spotify.'),
                SizedBox(height: (mediaQuery.size.height - mediaQuery.padding.top) * 0.08,),
                ElevatedButton(
                  onPressed: () {},
                  child: const Text('SIGN UP FREE'),
                  style: ElevatedButton.styleFrom(
                    primary: const Color.fromRGBO(98, 205, 107, 1),
                    textStyle: const TextStyle(
                      fontFamily: 'Gotham',
                      fontWeight: FontWeight.bold,
                      fontSize: 14
                    ),
                    minimumSize: Size(double.infinity, 50),
                    shape: const StadiumBorder(),
                  ),
                ),
                SizedBox(height: 15,),
                ElevatedButton(
                  onPressed: _onGoogleSignInBtnClicked,
                  child: const Text('CONTINUE WITH GOOGLE'),
                  style: ElevatedButton.styleFrom(
                    primary: Colors.transparent,
                    elevation : 0,
                    textStyle: const TextStyle(
                        fontFamily: 'Gotham',
                        fontWeight: FontWeight.bold,
                        fontSize: 14
                    ),
                    side: const BorderSide(
                      width: 3,
                      color: Colors.white
                    ),
                    minimumSize: const Size(double.infinity, 50),
                    shape: const StadiumBorder(),
                  ),
                ),
                SizedBox(height: 15,),
                ElevatedButton(
                    onPressed: (){},
                    child: const Text('Log In'),
                    style: ElevatedButton.styleFrom(
                      primary: Colors.transparent,
                      elevation : 0,
                      textStyle: const TextStyle(
                          fontFamily: 'Gotham',
                          fontWeight: FontWeight.bold,
                          fontSize: 14
                      ),
                      minimumSize: const Size(double.infinity, 50),
                      shape: const StadiumBorder(),
                    ),
                ),
              ],
            ),
          ),
        ),
      )
    );
  }
}
