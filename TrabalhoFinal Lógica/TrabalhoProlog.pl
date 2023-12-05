% Quadro 1 - Informações quadrimestrais dos vendedores internos.

% Escopo (Periodo, Vendedor, QtdAtendimentos, QtdVendas,
% nrAvaliacoesPositivas, NrAvaliacoesNegativas.

%quadrimestre 1
vendas(quad1,alberto,40,12,18,22).
vendas(quad1,barbara,25,25,24,1).
vendas(quad1,carol,8,30,25,23).
vendas(quad1,deivid,15,10,12,3).
vendas(quad1,ester,8,0,8,0).

%quadrimestre 2
vendas(quad2,alberto,16,0,4,12).
vendas(quad2,barbara,32,24,20,12).
vendas(quad2,carol,20,10,15,5).
vendas(quad2,deivid,30,30,20,10).
vendas(quad2,ester,18,2,2,16).

%quadrimestre 3
vendas(quad3,alberto,10,0,5,5).
vendas(quad3,barbara,18,10,12,6).
vendas(quad3,carol,21,16,18,3).
vendas(quad3,deivid,25,19,18,7).
vendas(quad3,ester,20,12,10,10).


%funcoes

bonificacao1(X1,X2) :- vendas(X1,X2,X3,X4,_,_), X3=X4.

bonificacao2(X1,X2) :- vendas(X1,X2,X3,X4,X5,_), X3=X4, X5 >= 0.8*X3.

notificacao(X2) :- vendas(_,X2,X3,X4,_,X6),((X6>=0.5*X3);(X4=<0.25*X3)).

demitido(X2) :- (vendas(quad1,X2,_,0,_,_), vendas(quad2,X2,_,0,_,_));(vendas(quad1,X2,_,0,_,_), vendas(quad3,X2,_,0,_,_));(vendas(quad2,X2,_,0,_,_), vendas(quad3,X2,_,0,_,_)).

homem(alberto).
homem(deivid).
mulher(barbara).
mulher(carol).
mulher(ester).





