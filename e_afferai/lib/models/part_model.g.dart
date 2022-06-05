// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'part_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Part _$PartFromJson(Map<String, dynamic> json) => Part(
      id: json['id'] as String,
      name: json['name'] as String,
      description: json['description'] as String,
      price: (json['price'] as num).toDouble(),
      partType: json['partType'] as String,
      partState: json['partState'] as String,
      qte: json['qte'] as int
      //seller: Seller.fromJson(json['seller'] as Map<String, dynamic>),
    );

Map<String, dynamic> _$PartToJson(Part instance) => <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'description': instance.description,
      'price': instance.price,
      'partType': instance.partType,
      'partState': instance.partState,
      'qte': instance.qte,
      'seller': instance.seller,
    };
