import 'package:flutter/material.dart';

class ShoppingBagScreen extends StatefulWidget {
  const ShoppingBagScreen({Key? key}) : super(key: key);

  @override
  State<ShoppingBagScreen> createState() => _ShoppingBagScreenState();
}

class _ShoppingBagScreenState extends State<ShoppingBagScreen> {
  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    return Scaffold(
        body: SingleChildScrollView(
          child: Container(
            width: double.infinity,
            padding: const EdgeInsets.only(top: 60, bottom: 15, left: 20, right: 20),
            child: Column(
              children: [
                SizedBox(
                  width: double.infinity,
                  height: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.07,
                  child: Row(
                    children: const [
                      Icon(
                        Icons.shopping_bag,
                        color: Color.fromRGBO(71, 80, 61, 1.0),
                      ),
                      SizedBox(width: 5),
                      Text(
                        "Shopping Cart",
                        style: TextStyle(
                          fontSize: 28,
                          fontWeight: FontWeight.bold,
                        ),
                        textAlign: TextAlign.start,
                      ),
                    ],
                  ),
                ),
                Container(
                  padding: EdgeInsets.only(top: (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.25),
                  width: (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.75,
                  child: Image.asset("assets/images/empty_cart.png")
                ),
                const SizedBox(height: 50),
                const Text(
                  "No Plant Yet",
                  style: TextStyle(
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                      color: Colors.black
                  ),
                ),
                const SizedBox(height: 10),
                const Text(
                  "Add your plant to cart\nby click add to cart button",
                  style: TextStyle(
                      fontSize: 14,
                      color: Colors.grey
                  ),
                  textAlign: TextAlign.center,
                ),
              ],
            ),
          ),
        )
    );
  }
}
