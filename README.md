
## Настройка
Перед использованием проекта его необходимо настроить.

**Настройки помеченные `локально` находятся в gitignore, поэтому их нужно настроить вручную при каждом новом клонировании проекта.**

### gradle.properties (обязательно)
Поля для настройки:
* `version` - версия мода, может содержать любые символы
* `group` - путь до вашего репозитория на гитхабе, используется для публикации исходного кода мода
* `rootProjectName` - название вашего мода, используется как префикс jar файлов **(вместо пробелов использовать `-`)**

Пример:
```properties
version = v2.5.1dev
group = nekit508/ExampleMod
rootProjectName = example-mod
```

### version.properties (обязательно)
**В РАЗРАБОТКЕ (НЕ ТРОГАТЬ)**

### local.properties (локально)
Поля для настройки:
* `useandroid` - будет ли мобильная версия мода собираться.
* `sdkroot` - путь до android sdk, требуется для настройки, только если useandroid установлен на `true`

### copy.properties (локально)
Файл для настройки мест копирования собранных jar файлов.

`название подпроекта` = `директория для копирования` (перечисляются через `;`)

Пример:
```properties
desktop = D:/games/mindustry/mods;D:/mods
android = D:/mods
```

**Если вы используете русские символы в пути, убедитесь, что файл записан в кодировке UTF-8**

### APA.properties и [subproject name]/APA.properties (локально)
Глобальные (для всех подпроектов) и локальные (для конкретного подпроекта) настройки аргументов для АП (аннотационных процессоров).

`агрумент1` = `значение1`

Пример:
```properties
ProjectName=desktop
LoadsOutput=files.loads
```

## Сборка

```shell
./gradlew deploy
```

Для сборки одного подпроекта использовать:
```shell
./gradlew [subproject name]:cjar
```

По окончании сборки все выходные файлы окажутся в директории artifacts, а также в местах для копирования.

## GitHub workflows

При `push` или `pull request` мод собирается на серверах гитхаба, после чего можно скачать полученные артифакты (`annotations`, `core`, `desktop`, `android`).

## Публикация на JitPack.io

Шаблон поддерживает возможность публикации на JitPack.
При сборке проекта в логи выводятся пути зависимостей мода.

Формирование пути зависимости:

`github.[mod group]:[subproject name]:[mod version]`
