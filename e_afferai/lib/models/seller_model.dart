import 'package:json_annotation/json_annotation.dart';

part 'seller_model.g.dart';

@JsonSerializable()
class Seller{
  String id;
  String firstName;
  String lastName;
  String phoneNumber;
  String city;
  String address;

  Seller({required this.id,required this.firstName,required this.lastName,required this.phoneNumber,
    required this.city, required this.address});

  factory Seller.fromJson(Map<String,dynamic> parsedJson)=> _$SellerFromJson(parsedJson);

  Map<String, dynamic> toJson() => _$SellerToJson(this);

}