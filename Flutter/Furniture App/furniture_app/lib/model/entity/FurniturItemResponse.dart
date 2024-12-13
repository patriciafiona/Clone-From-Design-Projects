import 'package:json_annotation/json_annotation.dart';

import 'DimensionsResponse.dart';

@JsonSerializable()
class FurniturItemResponse {
  int? id;
  String? category;
  String? name;
  double? price;
  int? discountPercentage;
  String? brand;
  String? color;
  Dimensions? dimensions;
  int? seatHeight;
  int? seatDepth;
  List<String>? photos;
  String? url;

  FurniturItemResponse(
      {this.id,
        this.category,
        this.name,
        this.price,
        this.discountPercentage,
        this.brand,
        this.color,
        this.dimensions,
        this.seatHeight,
        this.seatDepth,
        this.photos,
        this.url});

  FurniturItemResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    category = json['category'];
    name = json['name'];
    price = json['price'];
    discountPercentage = json['discount_percentage'];
    brand = json['brand'];
    color = json['color'];
    dimensions = json['dimensions'] != null
        ? new Dimensions.fromJson(json['dimensions'])
        : null;
    seatHeight = json['seat_height'];
    seatDepth = json['seat_depth'];
    photos = json['photos'].cast<String>();
    url = json['url'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['category'] = category;
    data['name'] = name;
    data['price'] = price;
    data['discount_percentage'] = discountPercentage;
    data['brand'] = brand;
    data['color'] = color;
    if (dimensions != null) {
      data['dimensions'] = dimensions!.toJson();
    }
    data['seat_height'] = seatHeight;
    data['seat_depth'] = seatDepth;
    data['photos'] = photos;
    data['url'] = url;
    return data;
  }
}