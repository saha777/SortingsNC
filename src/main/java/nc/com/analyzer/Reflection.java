package nc.com.analyzer;

import nc.com.analyzer.annotations.Filler;
import nc.com.fillers.ArrayType;
import nc.com.sorters.SortType;
import nc.com.sorters.Sorter;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Reflection {
    /**
     * This method returns reflections that will be
     * used for searching classes in package nc.com.sorters.
     */
    private Reflections getSortReflections() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        return new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("nc.com.sorters"))));
    }

    /**
     * This method returns reflections that will be used for searching methods in package nc.com.fillers.
     */
    private Reflections getArraysReflections() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        return new Reflections(new ConfigurationBuilder()
                .setScanners(
                        new SubTypesScanner(false /* don't exclude Object.class */),
                        new ResourcesScanner(),
                        new MethodAnnotationsScanner()
                )
                .setUrls(
                        ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0]))
                )
                .filterInputsBy(
                        new FilterBuilder().include(FilterBuilder.prefix("nc.com.fillers"))
                )
        );
    }

    /**
     * Return an array from the method annotated as
     * nc.com.analyzer.annotations.Filler from the class
     * nc.com.filler.ArraysFiller.
     *
     * @param      arrayType      the type of array filler.
     */
    public int[] getArray(ArrayType arrayType)
            throws InvocationTargetException, IllegalAccessException {
        int[] array = null;

        Set<Method> methods = getArraysReflections().getMethodsAnnotatedWith(Filler.class);

        Iterator<Method> iMethods = methods.iterator();

        if(iMethods.hasNext()) {
            Method method = iMethods.next();
            array = (int[]) method.invoke(method.getReturnType(), arrayType);
        }

        return array;
    }

    /**
     * Return an sub type of nc.sorters.Sorter.
     *
     * @param      sortType      the type of sorter.
     */
    public Class<? extends Sorter> getSorter(SortType sortType){
        Set<Class<? extends Sorter>> sorters = getSortReflections().getSubTypesOf(Sorter.class);

        for(Class<? extends Sorter> sorter : sorters)
            if(sorter.getSimpleName().equals(sortType.toString()))
                return sorter;

        return null;
    }

    /**
     * This method sorts the array passed to it,
     * measures and returns time of it.
     *
     * @param      sorter      the type of sorter.
     * @param      array       the array which must be sorted
     */
    public long sort(Class<? extends Sorter> sorter, int[] array)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException{
        Constructor<? extends Sorter> constructor = sorter.getConstructor(int[].class);
        Sorter sorterInstance = constructor.newInstance(array);

        long time = System.nanoTime();
        sorterInstance.sort();
        time = System.nanoTime() - time;

        return time;
    }
}
