# demo 는 여러 기능들을 기록해 놓은 프로젝트 입니다. 
--- 

## Spring @Async 란
> @Async Annotation은 Spring에서 제공하는 Thread Pool을 활용하는 비동기 메소드 지원 Annotation이다.      
> java.util.concurrent.ExecutorService을 활용해서 비동기 방식의 method를 정의 할 때마다, Runnable의 run()을 재구현해야 하는 등 동일한 작업들의 반복이 잦았다.   
@Async Annotation을 활용하면 손쉽게 비동기 메소드 작성이 가능하고 만약 Spring Boot에서 간단히 사용하고 싶다면, 단순히 Application Class에 @EnableAsync Annotation을 추가하고,
비동기로 작동하길 원하는 method 위에 @Async Annotation을 붙여주면 사용할 수 있다.
### AsyncConfigurerSupport 설정
- @Configuration : Spring 설정 관련 Class로 @Component 등록되어 Scanning 될 수 있다.
- @EnableAsync : Spring method에서 비동기 기능을 사용가능하게 활성화 한다.
- CorePoolSize : 기본 실행 대기하는 Thread의 수**
- MaxPoolSize : 동시 동작하는 최대 Thread의 수
- QueueCapacity : MaxPoolSize 초과 요청에서 Thread 생성 요청시,   
해당 요청을 Queue에 저장하는데 이때 최대 수용 가능한 Queue의 수,   
Queue에 저장되어있다가 Thread에 자리가 생기면 하나씩 빠져나가 동작   
- ThreadNamePrefix : 생성되는 Thread 접두사 지정

--- 
## Kafka 란
> 분산형 스트리밍 플랫폼(A distributed streaming platform)이다.    
메시지를 받기를 원하는 사람이 해당 토픽(topic)을 구독함으로써 메시지를 읽어 올 수 있다.    
> Producer에서는 메시지를 입력하고, Consumer에서 메시지를 읽어갈때 Zookeeper에서 broker 및 offset정보를 관리하기 때문에 분산처리가 가능하게 된다.

### 특징
- 대용량 실시간 로그처리에 특화되어 설계된 메시징 시스템으로    
메시지를 메모리에 저장하는 기존 메시징 시스템과는 달리 파일에 저장을 하는데 그로 인해 카프카를 재시작해도 메시지 유실 우려가 감소된다.
> 컨슈머(Consumer)가 브로커(Broker)로부터 메시지를 직접 가져가는 PULL 방식으로 동작하기 때문에 컨슈머는 자신의 처리 능력만큼의 메시지만 가져와 최적의 성능을 낼 수 있다

### 구성
#### topic 
> 카프카에 저장되는 메시지는 topic으로 분류, topic은 여러개의 patition으로 나눠진다.
#### partition
> - 메세지를 저장하는 물리적인 파일
> - 한 개의 토픽은 한 개 이상의 파티션으로 구성됨 
> - 파티션은 메시지 추가만 가능한 파일(append-only)
> - message의 상대적 위치를 나타내는 offset이 있다.
#### offset
> - 파티션내 각 메시지의 저장된 상대적 위치   
> - 프로듀서가 넣은 메시지는 파티션의 맨 뒤에 추가 (Queue)
> - 컨슈머는 오프셋 기준으로 마지막 커밋 시점부터 메시지를 순서대로 읽어서 처리함
> - 파티션의 메시지 파일은 처리 후에도 계속 저장되어 있며 설정에 따라 일정시간 뒤 삭제됨

#### producer
> - 메시지(이벤트)를 발행하여 생산(Wirte) 하는 주체
> - 프로듀서는 메시지 전송시 토픽을 지정
> - 파티션은 라운드로빈 방식 혹은 파티션 번호를 지정하여 넣을 수 있음
> - 같은 키를 갖는 메시지는 같은 파티션에 저장 되며 순서 유지
#### consumer
> 메시지(이벤트)를 구독하며 소비(Read)하는 주체

#### consumer group
> - 메시지를 소비하는 컨슈머들의 논리적 그룹
> - Topic의 파티션은 컨슈머그룹과 1:N 매칭 관계로 동일 그룹내 한 개의 컨슈머만 연결 되기 때문에 파티션의 메시지는 순서대로 처리되도록 보장
> - 특정 컨슈머에 문제가 생겼을때 Fail over를 통한 리밸런싱 가능
> - 보통 파티션과 컨슈머는 1:1이 best practice로 본다.
#### broker 
> broker는 카프카 서버를 칭한다. 동일한 노드내에서 여러개의 broker서버를 띄울 수 있다. 
#### zookeeper
> 카프카 클러스터 정보 및 분산처리 관리 등 메타데이터 저장. 카프카를 띄우기 위해 반드시 실행되어야 한다.
#### replication
> 카프카에서는 replication 수를 임의로 지정하여 topic를 만들 수 있다. replication-factor에 지정하는데 만약 3으로 하면 replication 수가 3이 된다.
>

