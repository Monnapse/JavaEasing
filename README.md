# Java Easing
 Ease functions

# How to use

### Creating a Class
Smoothly ease a value.

***easeType:*** The type of easing to be used.

***easeDirection:*** The direction of the easeType.

***duration:*** The duration of the ease.

```java
Easing TestTween = new Easing(EasingType.Sine, EasingDirection.InOut, 0.5);
```

### Setting the value
Sets the value.

***value:*** The starting value.

***endValue:*** The ending value.

```java
TestTween.SetValue(0, 15);
```

### Updating
Updates the current value based on current time since value has changed.

```java
TestTween.Update();
```

### Getting the value
Get the actual value (Adds onto previous operation value).

```java
TestTween.GetActualValue();
```

# Example
```java
Easing TestTween = new Easing(EasingType.Sine, EasingDirection.InOut, 0.5);
TestTween.SetValue(0, 15);

while (true) {
    TestTween.Update();

    System.out.printf(
        "%nPercentage: %s, Value: %s %n",
        TestTween.GetPercentage(),
        TestTween.GetActualValue()
    );

    Thread.sleep(10);
}
```