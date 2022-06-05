import 'dart:convert';

import 'package:json_annotation/json_annotation.dart';
import  'seller_model.dart';

part 'part_model.g.dart';


@JsonSerializable()
class Part{
  String? id;
  String? name;
  //String images[];
  String? description;
  double? price;
  String? partType;
  String? partState;
  int? qte;
  Seller? seller;
  String? error;

  Part.withError(String errorMessage) {
    error = errorMessage;
  }

  Part({required this.id,required this.name,required this.description,required this.price,required this.partType,
    required this.partState, required this.qte/*,required this.seller*/});

  factory Part.fromJson(Map<String,dynamic> parsedJson)=> _$PartFromJson(parsedJson);

  Map<String, dynamic> toJson() => _$PartToJson(this);

}