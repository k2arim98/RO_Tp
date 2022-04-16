# RO_Tp

# Déterminer les deux villes à choisir pour minimiser les coûts de transport

# La modélisation de problème :
  Les variables de décision :
    flow_ijkm(i)(j)(k)(m) : variable boolean indiquant si le fret transporté de i à j passe par les hubs k et m
    hub(i) : variable boolean indiquant si la ville possède un hub

# Les contraintes :
  la somme de flow_ijkm(i)(j)(k)(m) = 1
  flow_ijkm(i)(j)(k)(m) = hub(k)
  flow_ijkm(i)(j)(k)(m) = hub(m)

# La fonction objectif :
min Z = la somme de produit flow_ijkm(i)(j)(k)(m)*Q(i)(j)*C(i)(j)(k)(m)

# la resultat :
Z = 3.9498e+7 euro
hub=(000011) : les deux villes sont Nice et Paris
