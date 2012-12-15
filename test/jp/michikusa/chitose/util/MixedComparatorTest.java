package jp.michikusa.chitose.util;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class MixedComparatorTest{
    @Before
    public void prepare(){
        this.origins= Lists.newArrayList();
        
        this.origins.add(new Sample("a", "b", "a", 5));
        this.origins.add(new Sample("a", "a", "a", 4));
        this.origins.add(new Sample("b", "a", "a", 3));
        this.origins.add(new Sample("b", "b", "a", 2));
        this.origins.add(new Sample("c", "c", "a", 1));
        this.origins.add(new Sample("c", "c", "a", 9));
    }
    
    @Test
    public void first_nameのみ昇順ソート(){
        MixedComparator<Sample> comp= MixedComparator.<Sample>builder() //
                .add(this.first_name_comparator) //
                .build();
        
        Collections.sort(this.origins, comp);
        
        for(int i= 1; i < this.origins.size(); ++i){
            String lower= this.origins.get(i - 1).first_name;
            String higher= this.origins.get(i).first_name;
            
            assertTrue(lower.compareTo(higher) <= 0);
        }
    }
    
    @Test
    public void first_nameのみ降順ソート(){
        MixedComparator<Sample> comp= MixedComparator.<Sample>builder() //
                .add(Collections.reverseOrder(this.first_name_comparator)) //
                .build();
        
        Collections.sort(this.origins, comp);
        
        for(int i= 1; i < this.origins.size(); ++i){
            String higher= this.origins.get(i - 1).first_name;
            String lower= this.origins.get(i).first_name;
            
            assertTrue(higher.compareTo(lower) >= 0);
        }
    }
    
    @Test
    public void 比較器1個(){
        MixedComparator<String> comp= MixedComparator.<String>builder() //
                .add(Comparators.makeAscComparator(String.class)) //
                .build();
        
        assertTrue(comp.compare("", "") == 0);
        assertTrue(comp.compare("a", "a") == 0);
        assertTrue(comp.compare("a", "b") < 0);
        assertTrue(comp.compare("b", "a") > 0);
    }
    
    private static final class Sample{
        public final String first_name;
        public final String middle_name;
        public final String last_name;
        public final int    age;
        
        public Sample(String first_name, String middle_name, String last_name, int age){
            this.first_name= first_name;
            this.middle_name= middle_name;
            this.last_name= last_name;
            this.age= age;
        }
        
        @Override
        public int hashCode(){
            final int prime= 31;
            int result= 1;
            result= prime * result + age;
            result= prime * result + ((first_name == null) ? 0 : first_name.hashCode());
            result= prime * result + ((last_name == null) ? 0 : last_name.hashCode());
            result= prime * result + ((middle_name == null) ? 0 : middle_name.hashCode());
            return result;
        }
        
        @Override
        public boolean equals(Object obj){
            if(this == obj) return true;
            if(obj == null) return false;
            if(getClass() != obj.getClass()) return false;
            Sample other= (Sample)obj;
            if(age != other.age) return false;
            if(first_name == null){
                if(other.first_name != null) return false;
            }
            else if( !first_name.equals(other.first_name)) return false;
            if(last_name == null){
                if(other.last_name != null) return false;
            }
            else if( !last_name.equals(other.last_name)) return false;
            if(middle_name == null){
                if(other.middle_name != null) return false;
            }
            else if( !middle_name.equals(other.middle_name)) return false;
            return true;
        }
        
        @Override
        public String toString(){
            return "Sample [first_name=" + first_name + ", middle_name=" + middle_name
                    + ", last_name=" + last_name + ", age=" + age + "]";
        }
    }
    
    private final Comparator<Sample> first_name_comparator  = new Comparator<MixedComparatorTest.Sample>(){
                                                                @Override
                                                                public int compare(Sample lhs,
                                                                        Sample rhs){
                                                                    return lhs.first_name
                                                                            .compareTo(rhs.first_name);
                                                                }
                                                            };
    
    private final Comparator<Sample> middle_name_comparator = new Comparator<MixedComparatorTest.Sample>(){
                                                                @Override
                                                                public int compare(Sample lhs,
                                                                        Sample rhs){
                                                                    return lhs.middle_name
                                                                            .compareTo(rhs.middle_name);
                                                                }
                                                            };
    
    private final Comparator<Sample> last_name_comparator   = new Comparator<MixedComparatorTest.Sample>(){
                                                                @Override
                                                                public int compare(Sample lhs,
                                                                        Sample rhs){
                                                                    return lhs.last_name
                                                                            .compareTo(rhs.last_name);
                                                                }
                                                            };
    private final Comparator<Sample> age_comparator         = new Comparator<MixedComparatorTest.Sample>(){
                                                                @Override
                                                                public int compare(Sample lhs,
                                                                        Sample rhs){
                                                                    return rhs.age - lhs.age;
                                                                }
                                                            };
    
    private List<Sample>             origins;
}
