import 'package:json_annotation/json_annotation.dart';

@JsonSerializable()
class FurniturItemResponse {
  int? id;
  String? category;
  String? name;
  int? price;
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
    final Map<String, dynamic> data = Map<String, dynamic>();
    data['id'] = this.id;
    data['category'] = this.category;
    data['name'] = this.name;
    data['price'] = this.price;
    data['discount_percentage'] = this.discountPercentage;
    data['brand'] = this.brand;
    data['color'] = this.color;
    if (this.dimensions != null) {
      data['dimensions'] = this.dimensions!.toJson();
    }
    data['seat_height'] = this.seatHeight;
    data['seat_depth'] = this.seatDepth;
    data['photos'] = this.photos;
    data['url'] = this.url;
    return data;
  }
}

@JsonSerializable()
class Dimensions {
  int? width;
  int? height;
  int? dimension;

  Dimensions({this.width, this.height, this.dimension});

  Dimensions.fromJson(Map<String, dynamic> json) {
    width = json['width'];
    height = json['height'];
    dimension = json['dimension'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = Map<String, dynamic>();
    data['width'] = this.width;
    data['height'] = this.height;
    data['dimension'] = this.dimension;
    return data;
  }
}