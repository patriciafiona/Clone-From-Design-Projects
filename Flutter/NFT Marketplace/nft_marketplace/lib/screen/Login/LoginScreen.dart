import 'package:flutter/material.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({Key? key}) : super(key: key);
  static const routeName = '/login_screen';

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
      extendBodyBehindAppBar: true,
      body: Container(
        decoration: const BoxDecoration(
          image: DecorationImage(
            image: AssetImage("assets/images/background.png"),
            fit: BoxFit.cover,
          ),
        ),
        width: double.infinity,
        child:
        SingleChildScrollView(
            padding: const EdgeInsets.only(bottom: 20),
            child: Column(
              children: [
                Container(
                  width: (mediaQuery.size.width - mediaQuery.padding.horizontal),
                  height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.65,
                  foregroundDecoration: const BoxDecoration(
                    gradient: LinearGradient(
                      colors: [
                        Colors.black, Colors.transparent,
                        Colors.transparent, Colors.black],
                      begin: Alignment.topCenter,
                      end: Alignment.bottomCenter,
                      stops: [0, 0.4, 0.7, 1],
                    ),
                  ),
                  child: Image.asset(
                      "assets/images/NFT_Poster_Cropped.png",
                      fit: BoxFit.cover,
                      width: double.infinity,
                  ),
                ),
                Container(
                  padding: const EdgeInsets.all(20),
                  decoration: const BoxDecoration(
                    color: Colors.black
                  ),
                  width: double.infinity,
                  child: 
                    const Text(
                      "Create your \nown NFTs \ndream gallery",
                      style: TextStyle(
                        fontFamily: 'FredokaOne',
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                        fontSize: 38,
                      ),
                    )
                ),
                Row(
                  children: [
                    Flexible(
                        flex: 1,
                        child: Container(
                          margin: const EdgeInsets.only(left: 20, top: 10, bottom: 10, right: 10),
                          decoration: const BoxDecoration(
                            color: Color.fromRGBO(61, 61, 61, 1)
                          ),
                          child: IconButton(
                            icon: Image.asset('assets/images/apple_logo.png'),
                            iconSize: 40,
                            onPressed: () {},
                          ),
                        )
                    ),
                    Flexible(
                        flex: 1,
                        child: Container(
                          margin: const EdgeInsets.all(10),
                          decoration: const BoxDecoration(
                              color: Color.fromRGBO(61, 61, 61, 1)
                          ),
                          child: IconButton(
                            icon: Image.asset('assets/images/google_logo.png'),
                            iconSize: 40,
                            onPressed: () {},
                          ),
                        )
                    ),
                    Expanded(
                        child: Container(
                          margin: const EdgeInsets.only(top: 20, bottom: 20, left: 10),
                          decoration: const BoxDecoration(
                              color: Color.fromRGBO(83, 242, 109, 1)
                          ),
                          child: TextButton(
                            style: ButtonStyle(
                              foregroundColor: MaterialStateProperty.all<Color>(Colors.blue),
                            ),
                            onPressed: () { },
                            child: const Text(
                              'Sign in',
                              style: TextStyle(
                                fontFamily: 'FredokaOne',
                                color: Colors.black,
                                fontSize: 14,
                              ),
                            ),
                          ),
                        )
                    )
                  ],
                )
              ],
            )
        ),
      ),
    );
  }
}
