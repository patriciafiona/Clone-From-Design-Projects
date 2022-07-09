import 'package:flutter/material.dart';
import 'package:plant_app/model/PlantItem.dart';
import 'package:plant_app/screen/DetailScreen.dart';

class CardPlantWidget extends StatelessWidget {
  final dynamic listData;

  const CardPlantWidget({Key? key, required this.listData}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        shrinkWrap: true,
        scrollDirection: Axis.horizontal,
        itemCount: listData?.length,
        itemBuilder: (BuildContext context, int index) {
          var data = listData?[index] as PlantItem;
          return GestureDetector(
            onTap: (){
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => DetailScreen(plantData: data) )
              );
            },
            child: Container(
              width: 220,
              padding: const EdgeInsets.all(10),
              child: Card(
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(15.0),
                ),
                color: const Color.fromRGBO(234, 233, 233, 1.0),
                child: Column(
                  children: [
                    Stack(
                      children: [
                        Container(
                          padding: const EdgeInsets.only(top: 10),
                          width: double.infinity,
                          height: 260,
                          child: Image.asset(data.imagePath)
                        ),
                        Container(
                          padding: const EdgeInsets.symmetric(vertical: 15, horizontal: 17),
                          width: double.infinity,
                          child: Text(
                            "\$${data.price.toString()}",
                            textAlign: TextAlign.right,
                            style: const TextStyle(
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                        Positioned(
                          width: 160,
                          height: 50,
                          top: 60,
                          left: -47,
                          child: RotationTransition(
                            turns: const AlwaysStoppedAnimation(-90 / 360),
                            child: Text(
                              data.name,
                              style: const TextStyle(
                                fontWeight: FontWeight.w500,
                                fontSize: 14,
                              ),
                            ),
                          ),
                        ),
                      ],
                    ),
                    Container(
                      padding: const EdgeInsets.only(left: 10, right: 10, bottom: 10),
                      child: Row(
                        children: [
                          Expanded(child:
                            ElevatedButton(
                              onPressed: () {},
                              child: const Text(
                                'Add to Cart',
                                style: TextStyle(
                                  color: Colors.black,
                                  fontSize: 12
                                ),
                              ),
                              style: ElevatedButton.styleFrom(
                                shape: const StadiumBorder(),
                                primary: Colors.white,
                                onPrimary: Colors.grey,
                              ),
                            )
                          ),
                          ElevatedButton(
                            onPressed: () {},
                            child: const Icon(
                              Icons.favorite_outline,
                              color: Colors.white,
                            ),
                            style: ElevatedButton.styleFrom(
                              shape: const CircleBorder(),
                              padding: const EdgeInsets.all(10),
                              primary: Colors.black,
                              onPrimary: Colors.grey,
                            ),
                          )
                        ],
                      ),
                    )
                  ],
                ),
              ),
            ),
          );
        }
    );
  }
}
