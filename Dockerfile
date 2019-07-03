FROM hseeberger/scala-sbt
WORKDIR /scrabble
ADD . /scrabble
CMD sbt run


# in CMD eingeben & fertig
#docker build -t scrabble:v2 .
#docker run scrabble:v2
