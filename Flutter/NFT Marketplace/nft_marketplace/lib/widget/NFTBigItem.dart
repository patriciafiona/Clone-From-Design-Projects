import 'package:flutter/material.dart';
import 'package:nft_marketplace/model/DataSource.dart';
import 'package:nft_marketplace/screen/Detail/DetailScreen.dart';

import '../model/entities/NFT.dart';

class NFTBigItem extends StatelessWidget {
  final NFT data;

  const NFTBigItem({
    super.key,
    required this.data
  });

  @override
  Widget build(BuildContext context) {
    final mediaQuery = MediaQuery.of(context);

    var creator = DataSource().creators.where(
            (c) => c.id == data.creatorId
    ).toList();

    double width = (mediaQuery.size.width - mediaQuery.padding.horizontal) * 0.8;
    double height = (mediaQuery.size.height - mediaQuery.padding.vertical) * 0.65;

    Widget checkVerified(){
      if(creator[0].isVerified){
        return const Icon(
          Icons.verified,
          color: Colors.blue,
          size: 10,
        );
      }else{
        return const SizedBox();
      }
    }

    return Container(
      width: width,
      height: height,
      margin: const EdgeInsets.symmetric(vertical: 20, horizontal: 10),
      child: Stack(
        children: [
          Container(
              padding: const EdgeInsets.all(10),
              width: width,
              height: height,
              decoration: BoxDecoration(
                image: DecorationImage(
                    image: AssetImage("assets/images/${data.imagePath}"),
                    fit: BoxFit.cover),
              )
          ),
          Positioned.fill(
            child: Align(
              alignment: Alignment.bottomCenter,
              child: Container(
                height: 90,
                margin: const EdgeInsets.only(top:0, left: 15, right: 15, bottom: 15),
                padding: const EdgeInsets.all(10.0),
                color: const Color.fromRGBO(40, 40, 40, .9),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Expanded(
                        child: Container(
                          padding: const EdgeInsets.only(left: 10),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                "${data.price} ETH",
                                style: const TextStyle(
                                    fontSize: 18,
                                    color: Colors.white,
                                    fontWeight: FontWeight.bold,
                                    fontFamily: 'FredokaOne'
                                ),
                              ),
                              Row(
                                children: [
                                  Text(
                                    creator[0].name,
                                    style: const TextStyle(
                                        fontSize: 10,
                                        color: Colors.grey,
                                        fontWeight: FontWeight.bold,
                                        fontFamily: 'FredokaOne'
                                    ),
                                  ),
                                  const SizedBox(width: 5),
                                  checkVerified()
                                ],
                              ),
                            ],
                          ),
                        )
                    ),
                    Expanded(
                        child: Container(
                          width: double.infinity,
                          margin: const EdgeInsets.symmetric(vertical: 10, horizontal: 15),
                          padding: const EdgeInsets.all(5),
                          decoration: const BoxDecoration(
                              color: Color.fromRGBO(83, 242, 109, 1)
                          ),
                          child: TextButton(
                            style: ButtonStyle(
                              foregroundColor: MaterialStateProperty.all<Color>(Colors.black),
                            ),
                            onPressed: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(builder: (context) => DetailScreen(
                                    data: data
                                )),
                              );
                            },
                            child: const Text(
                              'Bidding',
                              style: TextStyle(
                                fontFamily: 'FredokaOne',
                                color: Colors.black,
                                fontWeight: FontWeight.bold,
                                fontSize: 14,
                              ),
                            ),
                          ),
                        )
                    )
                  ],
                ),
              ),
            ),
          ),
        ]
      ),
    );
  }
}
