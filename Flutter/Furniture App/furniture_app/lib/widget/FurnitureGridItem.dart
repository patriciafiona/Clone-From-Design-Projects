import 'package:flutter/material.dart';
import 'package:furniture_app/model/entity/FurnituesResponse.dart';
import 'package:page_transition/page_transition.dart';

import '../model/entity/FurniturItemResponse.dart';
import '../screens/ProductDetails/ProductDetailScreen.dart';
import '../utils/Constants.dart';

class FurnitureGridItem extends StatelessWidget {
  const FurnitureGridItem({
    super.key,
    required this.furniture,
    required this.itemWidth
  });

  final FurniturItemResponse furniture;
  final double itemWidth;

  @override
  Widget build(BuildContext context) {
    return Card(
        color: transparentDarkBlue,
        elevation: 4,
        child: InkWell(
          splashColor: Colors.blue.withAlpha(30),
          onTap: () {
            Navigator.push(
                context,
                PageTransition(
                    duration: const Duration(milliseconds: 500),
                    type: PageTransitionType.fade,
                    child: ProductDetailScreen(
                      productId: furniture.id!,
                    )
                )
            );
          },
          child: Padding(
              padding: const EdgeInsets.only(left: 8, right: 8, top: 24, bottom: 0),
              child: Stack(
                children: [
                  Align(
                      alignment: Alignment.center,
                      child: Column(
                        children: [
                          Image.network(
                            "https://raw.githubusercontent.com/patriciafiona/patriciafiona.github.io/main/hosting/resouces/furnitures/${furniture.photos?[0]}",
                            fit: BoxFit.cover,
                            height: 130,
                            width: itemWidth - 20,
                            loadingBuilder: (BuildContext context, Widget child,
                                ImageChunkEvent? loadingProgress) {
                              if (loadingProgress == null) return child;
                              return Center(
                                child: CircularProgressIndicator(
                                  value: loadingProgress.expectedTotalBytes != null
                                      ? loadingProgress.cumulativeBytesLoaded /
                                      loadingProgress.expectedTotalBytes!
                                      : null,
                                ),
                              );
                            },
                          ),
                          const Spacer(),
                          Row(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              SizedBox(
                                width: itemWidth / 2.5,
                                child: Column(
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Text(
                                      furniture.name ?? "Unknown name",
                                      style: const TextStyle(
                                          fontSize: 12,
                                          color: Colors.white,
                                          fontFamily: "Lufga"
                                      ),
                                      overflow: TextOverflow.ellipsis,
                                      maxLines: 1,
                                    ),
                                    Text(
                                      "\$${furniture.price}",
                                      style: const TextStyle(
                                          fontSize: 15,
                                          color: Colors.white,
                                          fontFamily: "Lufga",
                                          fontWeight: FontWeight.bold
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                              const Spacer(),
                              ElevatedButton(
                                onPressed: () {},
                                style: ElevatedButton.styleFrom(
                                  shape: const CircleBorder(),
                                  padding: const EdgeInsets.all(8),
                                  backgroundColor: transparentGray, // <-- Button color
                                  foregroundColor: Colors.black, // <-- Splash color
                                ),
                                child: Transform.rotate(
                                  angle: -45,
                                  child: const Icon(
                                    Icons.arrow_forward,
                                    size: 18.0,
                                    color: Colors.white,
                                  ),
                                ),
                              )
                            ],
                          )
                        ],
                      )
                  ),
                  Align(
                    alignment: Alignment.topLeft,
                    child: ElevatedButton(
                        onPressed: () {},
                        style: ElevatedButton.styleFrom(
                          shape: const CircleBorder(),
                          padding: const EdgeInsets.all(4),
                          backgroundColor: transparentGray, // <-- Button color
                          foregroundColor: Colors.black, // <-- Splash color
                        ),
                        child: const Icon(
                          Icons.favorite_border_outlined,
                          size: 22.0,
                          color: Colors.white,
                        )
                    ),
                  )
                ],
              )
          ),
        )
    );
  }
}
