package com.hiscat.flink.ds.connector.kafka

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer.Semantic

import java.util.Properties

object FlinkKafkaProducerTest {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    env.setParallelism(1)

    val sink = new FlinkKafkaProducer[String](
      "yh001:9092",
      "test",
      new SimpleStringSchema()
//      , new Properties()
//      , Semantic.AT_LEAST_ONCE
    )

    env.fromElements("world")
      .addSink(sink)

    env.execute("flink kafka producer")
  }
}
