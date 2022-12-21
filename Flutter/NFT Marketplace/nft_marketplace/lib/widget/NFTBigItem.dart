import 'package:flutter/material.dart';
import 'package:nft_marketplace/model/DataSource.dart';

import '../model/entities/NFT.dart';

class NFTBigItem extends StatelessWidget {
  final NFT data;

  const NFTBigItem({
    super.key,
    required this.data
  });

  @override
  Widget build(BuildContext context) {

    var creator = DataSource().creators.where(
            (c) => c.id == data.creatorId
    ).toList();

    const double width = 270;
    const double height = 400;

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
                transform: Matrix4.translationValues(0.0, -95.0, 0.0),
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
                              Text(
                                creator[0].name,
                                style: const TextStyle(
                                    fontSize: 12,
                                    color: Colors.grey,
                                    fontWeight: FontWeight.bold,
                                    fontFamily: 'FredokaOne'
                                ),
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
                            onPressed: () { },
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
